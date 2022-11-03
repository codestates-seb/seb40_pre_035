import { useRef } from 'react';
import { Editor as Writer } from '@toast-ui/react-editor';
import prism from 'prismjs';
import 'prismjs/themes/prism.css';
import { fetchUploadImage } from '../../util/api';

import '@toast-ui/editor/dist/toastui-editor-viewer.css';
import '@toast-ui/editor/dist/toastui-editor.css';
import codeSyntaxHighlight from '@toast-ui/editor-plugin-code-syntax-highlight';
import '@toast-ui/editor-plugin-code-syntax-highlight/dist/toastui-editor-plugin-code-syntax-highlight.css';

function Editor({ onChange, height = '300px' }) {
  const editorRef = useRef();

  const onChangeHandle = () => {
    const markdown = editorRef.current.getInstance().getMarkdown();
    const json = JSON.stringify(markdown);
    return onChange(json);
  };

  const onUploadImage = async (blob, callback) => {
    console.log(blob);
    let path = await fetchUploadImage(blob);
    console.log(`${path}`);
    callback(path, blob.name);
    return false;
  };

  return (
    <div className="mb-4 editor-wrapper">
      <Writer
        previewStyle="tab"
        height={height}
        initialEditType="markdown"
        initialValue="## *Your* **markdown** here"
        ref={editorRef}
        plugins={[[codeSyntaxHighlight, { highlighter: prism }]]}
        hideModeSwitch={true}
        onChange={onChangeHandle}
        useCommandShortcut={false}
        hooks={{
          addImageBlobHook: onUploadImage,
        }}
      />
    </div>
  );
}

export default Editor;
