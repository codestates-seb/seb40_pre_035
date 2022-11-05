import { useRef, useEffect } from 'react';
import { Editor as Writer } from '@toast-ui/react-editor';
import prism from 'prismjs';
import 'prismjs/themes/prism.css';
import { fetchUploadImage } from '../../util/fetchFile';

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
    await fetchUploadImage(blob).then((path) => {
      console.log(path);
      callback(path, blob.name);
    });
    return false;
  };

  // useEffect(() => {
  //   if (isEditorClear) {
  //     editorRef.current
  //       .getInstance()
  //       .setMarkdown('## *Your* **markdown** here');
  //     setIsEditorClear(false);
  //   }
  // }, [isEditorClear]);

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
        toolbarItems={[
          ['heading', 'bold', 'strike'],
          ['hr', 'quote'],
          ['ul', 'ol', 'task'],
          ['table', 'image', 'link'],
          ['code', 'codeblock'],
        ]}
      />
    </div>
  );
}

export default Editor;
